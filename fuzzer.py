"""
Fuzzer for the Reliable 2048 CLI.

Structure follows the Fuzzing Book (Zeller et al.):
  https://www.fuzzingbook.org/

A Fuzzer generates inputs; a Runner executes the program under test and
classifies the outcome.  The two are kept separate so that different fuzzers
can be combined with the same runner, and vice-versa.
"""

import subprocess
import random
from typing import Tuple

# ---------------------------------------------------------------------------
# Outcome constants  (mirrors the Fuzzing Book convention)
# ---------------------------------------------------------------------------

PASS = "PASS"       # program exited normally, no errors reported
FAIL = "FAIL"       # program signalled a bug (non-zero exit, assertion error)
UNRESOLVED = "UNRESOLVED"  # e.g. timeout or unexpected error in the runner

# ---------------------------------------------------------------------------
# Valid input alphabet
# ---------------------------------------------------------------------------

KEYS = ['a', 's', 'w', 'd']   # left, down, up, right
QUIT = 'q'                     # graceful exit

# ---------------------------------------------------------------------------
# Runner
# ---------------------------------------------------------------------------

class Runner:
    """Runs the program under test and returns (output, outcome)."""

    def run(self, inp: str) -> Tuple[subprocess.CompletedProcess, str]:
        raise NotImplementedError


class CLIRunner(Runner):
    """
    Runs the 2048 CLI as a subprocess, feeding `inp` to its stdin.

    Returns a (CompletedProcess, outcome) pair where outcome is one of
    PASS, FAIL, or UNRESOLVED.
    """

    COMMAND = ['java', '-cp', './target/classes', 'ar.edu.unrc.game2048.MainCLI']
    TIMEOUT = 10  # seconds

    def run(self, inp: str) -> Tuple[subprocess.CompletedProcess, str]:
        try:
            result = subprocess.run(
                self.COMMAND,
                input=inp,
                capture_output=True,
                text=True,
                timeout=self.TIMEOUT
            )
        except subprocess.TimeoutExpired:
            return None, UNRESOLVED

        if result.returncode != 0 or result.stderr:
            return result, FAIL

        return result, PASS


# ---------------------------------------------------------------------------
# Fuzzer base class
# ---------------------------------------------------------------------------

class Fuzzer:
    """Base class: subclasses implement fuzz() to produce one input string."""

    def fuzz(self) -> str:
        """Return a single fuzzed input string."""
        raise NotImplementedError

    def run(self, runner: Runner) -> Tuple[subprocess.CompletedProcess, str]:
        """Fuzz once and run the result through *runner*."""
        return runner.run(self.fuzz())

    def runs(self, runner: Runner, trials: int = 10):
        """Fuzz *trials* times and return a list of (result, outcome) pairs."""
        return [self.run(runner) for _ in range(trials)]


# ---------------------------------------------------------------------------
# RandomFuzzer
# ---------------------------------------------------------------------------

class RandomFuzzer(Fuzzer):
    """
    Generates a random sequence of game moves followed by a quit command.

    Parameters
    ----------
    min_length : int
        Minimum number of move keys in the sequence.
    max_length : int
        Maximum number of move keys in the sequence.
    """

    def __init__(self, min_length: int = 10, max_length: int = 50):
        self.min_length = min_length
        self.max_length = max_length

    def fuzz(self) -> str:
        """
        TODO: Implement this method.

        Return a string representing a sequence of moves for the 2048 CLI.
        Each key must be on its own line (followed by '\\n').
        The sequence must end with the quit key ('q') on its own line.

        Use `self.min_length` and `self.max_length` to decide how many moves
        to generate, and pick keys randomly from the KEYS list.

        Example output for a sequence of 3 moves:
            'w\\na\\nd\\nq\\n'
        """
        raise NotImplementedError

# ---------------------------------------------------------------------------
# Main: run the fuzzer and report results
# ---------------------------------------------------------------------------

def main():
    runner = CLIRunner()
    fuzzer = RandomFuzzer(min_length=10, max_length=50)

    trials = 20
    outcomes = {PASS: 0, FAIL: 0, UNRESOLVED: 0}

    print(f"Running {trials} fuzzing trials...\n")

    for i in range(trials):
        inp = fuzzer.fuzz()
        result, outcome = runner.run(inp)

        outcomes[outcome] += 1

        print(f"--- Trial {i + 1:>3} | {outcome} ---")
        print(f"Input : {inp.replace(chr(10), ' ').strip()}")

        if result is not None:
            print(f"Exit  : {result.returncode}")
            if result.stdout:
                print(f"Output: {result.stdout.strip()}")
            if result.stderr:
                print(f"Stderr: {result.stderr.strip()}")

        print()

    print("=" * 50)
    print("Summary:")
    for outcome, count in outcomes.items():
        print(f"  {outcome:<12}: {count}/{trials}")


if __name__ == "__main__":
    main()
