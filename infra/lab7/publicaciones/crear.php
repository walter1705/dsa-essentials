<?php
require_once '../config/database.php';

$errors           = [];
$id_libro         = '';
$editorial        = '';
$fecha_publicacion = '';
$precio           = '';
$stock            = '0';

$conn = getConnection();
$libros = $conn->query("SELECT id, titulo FROM libros ORDER BY titulo");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id_libro          = trim($_POST['id_libro']          ?? '');
    $editorial         = trim($_POST['editorial']         ?? '');
    $fecha_publicacion = trim($_POST['fecha_publicacion'] ?? '');
    $precio            = trim($_POST['precio']            ?? '');
    $stock             = trim($_POST['stock']             ?? '0');

    if ($id_libro === '')  $errors[] = 'Debe seleccionar un libro.';
    if ($editorial === '') $errors[] = 'La editorial es obligatoria.';

    if (empty($errors)) {
        $fechaNull  = $fecha_publicacion !== '' ? $fecha_publicacion : null;
        $precioNull = $precio !== '' ? (float)$precio : null;
        $stockInt   = (int)$stock;
        $libroId    = (int)$id_libro;

        $stmt = $conn->prepare(
            "INSERT INTO publicaciones (id_libro, editorial, fecha_publicacion, precio, stock)
             VALUES (?, ?, ?, ?, ?)"
        );
        $stmt->bind_param('issdi', $libroId, $editorial, $fechaNull, $precioNull, $stockInt);
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

$pageTitle  = 'Nueva Publicación';
$activePage = 'publicaciones';
require_once '../includes/header.php';
?>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Nueva Publicación</h1>
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
                <label for="id_libro" class="form-label">Libro <span class="text-danger">*</span></label>
                <select id="id_libro" name="id_libro" class="form-control" required>
                    <option value="">— Seleccionar libro —</option>
                    <?php if ($libros): while ($l = $libros->fetch_assoc()): ?>
                    <option value="<?= $l['id'] ?>" <?= ($id_libro == $l['id']) ? 'selected' : '' ?>>
                        <?= htmlspecialchars($l['titulo']) ?>
                    </option>
                    <?php endwhile; endif; ?>
                </select>
            </div>
            <div class="mb-3">
                <label for="editorial" class="form-label">Editorial <span class="text-danger">*</span></label>
                <input type="text" id="editorial" name="editorial" class="form-control"
                       value="<?= htmlspecialchars($editorial) ?>" required>
            </div>
            <div class="mb-3">
                <label for="fecha_publicacion" class="form-label">Fecha de Publicación</label>
                <input type="date" id="fecha_publicacion" name="fecha_publicacion" class="form-control"
                       value="<?= htmlspecialchars($fecha_publicacion) ?>">
            </div>
            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" id="precio" name="precio" class="form-control"
                       step="0.01" min="0" value="<?= htmlspecialchars($precio) ?>">
            </div>
            <div class="mb-3">
                <label for="stock" class="form-label">Stock</label>
                <input type="number" id="stock" name="stock" class="form-control"
                       min="0" value="<?= htmlspecialchars($stock) ?>">
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
