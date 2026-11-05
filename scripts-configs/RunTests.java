import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.PostDiscoveryFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

/**
 * Corre clases de test JUnit 4 y JUnit 5 juntas (para usarlo con Chicory).
 *
 * Uso: RunTests [-x:paquete.Clase#metodo ...] paquete.Clase [paquete.Clase ...]
 * Los argumentos que empiezan con "-x:" excluyen ese método puntual.
 */
public class RunTests {
    private static final String EXCLUDE_PREFIX = "-x:";

    public static void main(String[] args) throws Exception {
        Set<String> excluded = new HashSet<>();
        LauncherDiscoveryRequestBuilder builder = LauncherDiscoveryRequestBuilder.request();
        for (String arg : args) {
            if (arg.startsWith(EXCLUDE_PREFIX)) {
                excluded.add(arg.substring(EXCLUDE_PREFIX.length()));
            } else {
                builder.selectors(selectClass(Class.forName(arg)));
            }
        }
        builder.filters(excludeMethods(excluded));

        LauncherDiscoveryRequest request = builder.build();
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.execute(request, listener);
        listener.getSummary().printTo(new PrintWriter(System.out));
        listener.getSummary().printFailuresTo(new PrintWriter(System.out));
    }

    private static PostDiscoveryFilter excludeMethods(Set<String> excluded) {
        return descriptor -> {
            boolean isExcluded = descriptor.getSource()
                    .filter(MethodSource.class::isInstance)
                    .map(MethodSource.class::cast)
                    .map(source -> source.getClassName() + "#" + source.getMethodName())
                    .filter(excluded::contains)
                    .isPresent();
            return isExcluded
                    ? FilterResult.excluded("excluido con " + EXCLUDE_PREFIX)
                    : FilterResult.included("no excluido");
        };
    }
}
