# 📚 Editorial DB — Instrucciones de Despliegue

Guía paso a paso para principiantes. Siga cada sección en orden.

---

## Requisitos previos

Necesita tener instalados en su máquina virtual:

- **Apache** (servidor web)
- **MariaDB** (base de datos)
- **PHP 8.x** (lenguaje del servidor)

Si no los tiene, instálelos con un solo comando en Ubuntu/Debian:

```bash
sudo apt update
sudo apt install apache2 mariadb-server php php-mysqli -y
```

---

## Paso 1 — Activar MariaDB y Apache

```bash
sudo systemctl start apache2
sudo systemctl start mariadb
sudo systemctl enable apache2
sudo systemctl enable mariadb
```

Verifique que están corriendo (debe decir `active (running)`):

```bash
sudo systemctl status apache2
sudo systemctl status mariadb
```

---

## Paso 2 — Asegurar MariaDB y crear el usuario (primera vez)

```bash
sudo mysql_secure_installation
```

Siga las instrucciones en pantalla. Cuando pregunte por contraseña del root puede presionar **Enter** si no definió una, o ingresar la que configuró.

---

## Paso 3 — Crear la base de datos

Abra el cliente de MariaDB:

```bash
sudo mysql -u root -p
```

(Si no configuró contraseña, omita `-p` o presione Enter cuando la solicite.)

Dentro del cliente MySQL ejecute el script de la base de datos:

```sql
SOURCE /ruta/completa/al/proyecto/lab7/db/database.sql;
```

> **Ejemplo concreto** si copió el proyecto en `/var/www/html/lab7`:
>
> ```sql
> SOURCE /var/www/html/lab7/db/database.sql;
> ```

Luego salga:

```sql
EXIT;
```

---

## Paso 4 — Copiar el proyecto a Apache

La carpeta pública de Apache es `/var/www/html/`. Copie el proyecto ahí:

```bash
sudo cp -r /ruta/donde/descargo/lab7 /var/www/html/lab7
```

Dé permisos de lectura a Apache:

```bash
sudo chown -R www-data:www-data /var/www/html/lab7
sudo chmod -R 755 /var/www/html/lab7
```

---

## Paso 5 — Configurar la conexión a la base de datos

Abra el archivo de configuración:

```bash
sudo nano /var/www/html/lab7/config/database.php
```

Edite las líneas según sus datos:

```php
define('DB_HOST', 'localhost');   // No cambie esto
define('DB_USER', 'root');        // Usuario de MariaDB
define('DB_PASS', '');            // Contraseña (déjela vacía si no puso)
define('DB_NAME', 'editorial_db');// No cambie esto
```

Guarde con **Ctrl + O**, luego **Enter**, luego **Ctrl + X**.

---

## Paso 6 — Abrir la aplicación en el navegador

Abra el navegador dentro de la máquina virtual y vaya a:

```
http://localhost/lab7/
```

Si accede desde otra máquina en la misma red, reemplace `localhost` por la IP de la VM:

```
http://192.168.X.X/lab7/
```

> Para conocer la IP de su VM ejecute: `ip a | grep inet`

---

## Estructura de archivos

```
lab7/
├── config/
│   └── database.php          ← Configuración de la BD
├── db/
│   └── database.sql          ← Script SQL (tablas + datos de prueba)
├── includes/
│   ├── header.php            ← Cabecera y barra de navegación
│   └── footer.php            ← Pie de página
├── autores/                  ← CRUD de Autores
│   ├── index.php
│   ├── crear.php
│   ├── editar.php
│   └── eliminar.php
├── libros/                   ← CRUD de Libros
│   ├── index.php
│   ├── crear.php
│   ├── editar.php
│   └── eliminar.php
├── publicaciones/            ← CRUD de Publicaciones
│   ├── index.php
│   ├── crear.php
│   ├── editar.php
│   └── eliminar.php
├── index.php                 ← Panel principal (dashboard)
└── INSTRUCCION.md            ← Este archivo
```

---

## Solución de problemas comunes

| Problema                   | Causa probable           | Solución                                                   |
| -------------------------- | ------------------------ | ---------------------------------------------------------- |
| Pantalla en blanco         | Error de PHP             | Revise los logs: `sudo tail -f /var/log/apache2/error.log` |
| "Error de conexión"        | Credenciales incorrectas | Revise `config/database.php`                               |
| Página no encontrada (404) | Proyecto no copiado      | Verifique que existe `/var/www/html/lab7/index.php`        |
| "Table doesn't exist"      | BD no creada             | Repita el Paso 3                                           |
| Sin permisos (403)         | Permisos de archivos     | Repita el comando `chown` del Paso 4                       |

---

## Cómo funciona la aplicación

- **Autores** → Registre los autores de los libros.
- **Libros** → Registre libros y asígneles un autor.
- **Publicaciones** → Registre cada edición/publicación de un libro (editorial, precio, stock).

El flujo recomendado es: primero cree **Autores** → luego **Libros** → luego **Publicaciones**.
