# Requerimientos Técnicos para Construir el Proyecto

## Entorno de Desarrollo:
- **Android Studio**: Versión Giraffe | 2022.3.1 Patch 1 (Build #AI-223.8836.35.2231.10671973, built on August 17, 2023).
  - **Runtime version**: 17.0.6+0-b2043.56-10027231 amd64
  - **VM**: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
  - **Sistema Operativo**: Windows 11.
  - **Memoria**: 1280 MB.
  - **Cores**: 8.

### SDK de Android:
- **Compile SDK**: Android SDK 33.
- **Min SDK**: Android SDK 24. Esto indica que la aplicación es compatible con dispositivos que ejecutan Android 7.0 (Nougat) o superior.
- **Target SDK**: Android SDK 33. Esto asegura que la app esté optimizada para dispositivos que ejecutan la última versión estable de Android al momento del desarrollo.

### Dependencias Clave:
- **Kotlin**: Versión estandarizada para Kotlin es 1.8 en JVM.
- **Retrofit y Moshi/Gson**: Para las operaciones de red y el manejo de JSON.
- **Room**: Para el almacenamiento local de datos.
- **Dagger Hilt**: Para la inyección de dependencias.
- **Android Architecture Components**: Incluyendo ViewModel y LiveData para manejar los datos de UI de manera eficiente.
- **Navigation Component**: Para manejar la navegación entre los fragmentos.
- **Coil**: Para la carga de imágenes (banderas de los países).
- **Coroutines de Kotlin**: Para la programación asíncrona.
- **HttpLoggingInterceptor**: Para el logging de las solicitudes de red.
  
### Compilación y Ejecución:
- **Gradle**: Utiliza Gradle como sistema de automatización de compilación. No es necesario instalar Gradle por separado ya que Android Studio lo integra.
- **Configuración de Build**: 
  - `debug`: Configuración estándar para el desarrollo.
  - `release`: Con `minifyEnabled` en `false` y configuración de ProGuard para optimizar y proteger el código al publicar la aplicación.

### Instrucciones para Compilar:
1. Clonar el repositorio desde GitHub.
2. Abrir el proyecto en Android Studio.
3. Asegurarse de que Android Studio tenga configurado el JDK 1.8.
4. Compilar el proyecto utilizando la opción 'Build -> Make Project' en Android Studio.
5. Para ejecutar la aplicación, utilizar un emulador o dispositivo físico compatible con Android SDK versión 24 o superior.

## Breve Descripción de la Responsabilidad de Cada Capa Propuesta
### Data (Datos):
Se divide en local y remoto, donde local utiliza Room para manejar la base de datos SQLite, y remoto contiene los modelos y el acceso a la API para obtener los datos de los países.
  - **Local**: Incluye DAOs y la base de datos que cachea los datos de los países.
  - **Remote**: Gestiona la comunicación con la API externa y convierte las respuestas en modelos para ser consumidos por el repositorio.

### Domain (Dominio):
Contiene las entidades y los repositorios, actuando como una capa intermedia entre la data y la UI, implementando la lógica de negocio.

### Presentation (Presentación):
Maneja la visualización de la UI, utilizando patrones como MVVM para vincular la lógica de la vista con el modelo de datos.
  - **Activities y Fragments**: Define las interfaces de usuario para mostrar las listas y los detalles de los países.
  - **ViewModel**: Prepara y gestiona los datos para la UI, interactuando con el dominio para obtener la información necesaria.

### DI (Inyección de Dependencias):
Configura los módulos de inyección de dependencias para proveer instancias de clases necesarias como las de la capa de datos y de dominio.

### Capturas de Pantalla de la Aplicación

#### Lista de Países
![Lista de Países](https://github.com/JonathanRamirez1/Explorer-Countries/blob/main/List_Country.jpg)

#### Detalle del País
![Detalle del País](https://github.com/JonathanRamirez1/Explorer-Countries/blob/main/Detail_Country.jpg)

