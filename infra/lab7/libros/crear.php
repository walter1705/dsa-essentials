<?php
require_once '../config/database.php';

$errors          = [];
$titulo          = '';
$isbn            = '';
$anio_publicacion = '';
$genero          = '';
$id_autor        = '';

$conn = getConnection();
$autores = $conn->query("SELECT id, nombre, apellido FROM autores ORDER BY apellido, nombre");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $titulo           = trim($_POST['titulo']           ?? '');
    $isbn             = trim($_POST['isbn']             ?? '');
    $anio_publicacion = trim($_POST['anio_publicacion'] ?? '');
    $genero           = trim($_POST['genero']           ?? '');
    $id_autor         = trim($_POST['id_autor']         ?? '');

    if ($titulo === '') $errors[] = 'El título es obligatorio.';

    if (empty($errors)) {
        $anioNull  = $anio_publicacion !== '' ? (int)$anio_publicacion : null;
        $isbnNull  = $isbn !== '' ? $isbn : null;
        $genNull   = $genero !== '' ? $genero : null;
        $autorNull = $id_autor !== '' ? (int)$id_autor : null;

        $stmt = $conn->prepare(
            "INSERT INTO libros (titulo, isbn, anio_publicacion, genero, id_autor) VALUES (?, ?, ?, ?, ?)"
        );
        $stmt->bind_param('ssssi', $titulo, $isbnNull, $anioNull, $genNull, $autorNull);
        if ($stmt->execute()) {
            $stmt->close();
            $conn->close();
            header('Location: index.php?msg=created');
            exit;
        } else {
            $errors[] = 'Error al guardar: ' . $stmt->error;
            $stmt->close();
        }
    }
}

$pageTitle  = 'Nuevo Libro';
$activePage = 'libros';
require_once '../includes/header.php';
?>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Nuevo Libro</h1>
    <a href="index.php" class="btn btn-secondary">
        <i class="bi bi-arrow-left-circle me-1"></i>Volver
    </a>
</div>

<?php if (!empty($errors)): ?>
    <div class="alert alert-danger">
        <ul class="mb-0">
            <?php foreach ($errors as $e): ?>
                <li><?= htmlspecialchars($e) ?></li>
            <?php endforeach; ?>
        </ul>
    </div>
<?php endif; ?>

<div class="card shadow-sm" style="max-width:620px;">
    <div class="card-body">
        <form method="POST" action="crear.php" novalidate>
            <div class="mb-3">
                <label for="titulo" class="form-label">Título <span class="text-danger">*</span></label>
                <input type="text" id="titulo" name="titulo" class="form-control"
                       value="<?= htmlspecialchars($titulo) ?>" required>
            </div>
            <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <input type="text" id="isbn" name="isbn" class="form-control"
                       value="<?= htmlspecialchars($isbn) ?>">
            </div>
            <div class="mb-3">
                <label for="anio_publicacion" class="form-label">Año de Publicación</label>
                <input type="number" id="anio_publicacion" name="anio_publicacion" class="form-control"
                       min="1900" max="2099" value="<?= htmlspecialchars($anio_publicacion) ?>">
            </div>
            <div class="mb-3">
                <label for="genero" class="form-label">Género</label>
                <input type="text" id="genero" name="genero" class="form-control"
                       value="<?= htmlspecialchars($genero) ?>">
            </div>
            <div class="mb-3">
                <label for="id_autor" class="form-label">Autor</label>
                <select id="id_autor" name="id_autor" class="form-control">
                    <option value="">— Sin autor —</option>
                    <?php if ($autores): while ($a = $autores->fetch_assoc()): ?>
                    <option value="<?= $a['id'] ?>" <?= ($id_autor == $a['id']) ? 'selected' : '' ?>>
                        <?= htmlspecialchars($a['apellido'] . ', ' . $a['nombre']) ?>
                    </option>
                    <?php endwhile; endif; ?>
                </select>
            </div>
            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-success">
                    <i class="bi bi-save-fill me-1"></i>Guardar
                </button>
                <a href="index.php" class="btn btn-outline-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>

<?php
$conn->close();
require_once '../includes/footer.php';
?>
