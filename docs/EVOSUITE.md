# Setup para EvoSuite

## Si solo corrés `mvn test`, jacoco o pitest

No hace falta nada nuevo: alcanza con **Java 17 o superior** (el de siempre).

## Si querés generar tests con EvoSuite

EvoSuite 1.0.6 solo corre sobre **Java 8**, así que necesitás tener un JDK 8 instalado **aparte** de tu Java de siempre.

### 1. Instalar un JDK 8

Tiene que ser el **JDK**, no el JRE: el script busca `lib/tools.jar` y el JRE no lo tiene.

**Windows:** descargá **Eclipse Temurin 8, JDK, `.zip`** desde https://adoptium.net/temurin/releases/?version=8 y descomprimilo en cualquier carpeta (por ejemplo `C:\Users\<vos>\.jdk\`). Usá el `.zip` y no el `.msi`, porque el instalador agrega Java 8 al `PATH`, y entonces Maven podría terminar usando Java 8.

**Linux:**

```bash
mkdir -p ~/.jdk
curl -L "https://api.adoptium.net/v3/binary/latest/8/ga/linux/x64/jdk/hotspot/normal/eclipse" | tar -xz -C ~/.jdk
ls ~/.jdk   # anotá el nombre de la carpeta, por ejemplo jdk8u504-b01
```

(En ARM cambiá `x64` por `aarch64`.) No lo pongas como Java por defecto: nada de `update-alternatives` ni de tocar el `PATH`.

### 2. Definir `JAVA8_HOME`

Tiene que apuntar a la carpeta del JDK 8 (la que contiene `bin/` y `lib/`). Reemplazá `jdk8u504-b01` por el nombre que te haya quedado.

Windows (PowerShell):

```powershell
[Environment]::SetEnvironmentVariable("JAVA8_HOME", "C:\Users\<vos>\.jdk\jdk8u504-b01", "User")
```

Linux:

```bash
echo 'export JAVA8_HOME="$HOME/.jdk/jdk8u504-b01"' >> ~/.bashrc   # o ~/.zshrc si usás zsh
```

Después **cerrá y volvé a abrir la terminal y el IDE** para que tomen la variable.

⚠️ **No cambies `JAVA_HOME` ni el `PATH`**: Maven tiene que seguir corriendo con Java 17+.

### 3. Generar los tests

Desde Git Bash (en Windows) o desde una terminal (en Linux):

```bash
bash runEvosuite.sh
```

El script baja EvoSuite si hace falta, compila, genera `Cell_ESTest.java` en `src/test/java` y corre `mvn test`. Para generar tests de otra clase, cambiá `TARGET_CLASS` en el script.

## Problemas comunes

| Mensaje | Solución |
|---|---|
| `Definí JAVA8_HOME apuntando a un JDK 8...` | La variable no está definida o no apunta a un JDK. Revisá el paso 2 y reabrí la terminal. |
| `Did not manage to automatically find tools.jar` | `JAVA8_HOME` apunta a un JRE. Instalá el JDK (paso 1). |

## Nota

Los tests se generan con `-Dno_runtime_dependency=true`: son tests JUnit 4 comunes y no usan el runtime de EvoSuite, que no funciona con Java 17+. Por eso, los tests que se generen para clases que usan azar (por ejemplo `Board`, con `Math.random()`) pueden fallar de forma intermitente.
