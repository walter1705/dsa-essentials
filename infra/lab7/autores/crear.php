<?php
require_once '../config/database.php';

$errors = [];
$nombre           = '';
$apellido         = '';
$nacionalidad     = '';
$fecha_nacimiento = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nombre           = trim($_POST['nombre']           ?? '');
    $apellido         = trim($_POST['apellido']         ?? '');
    $nacionalidad     = trim($_POST['nacionalidad']     ?? '');
    $fecha_nacimiento = trim($_POST['fecha_nacimiento'] ?? '');

    if ($nombre === '')   $errors[] = 'El nombre es obligatorio.';
    if ($apellido === '') $errors[] = 'El apellido es obligatorio.';

    if (empty($errors)) {
        $conn = getConnection();
        $stmt = $conn->prepare(
            "INSERT INTO autores (nombre, apellido, nacionalidad, fecha_nacimiento) VALUES (?, ?, ?, ?)"
        );
        $fnNull = $fecha_nacimiento !== '' ? $fecha_nacimiento : null;
        $stmt->bind_param('ssss', $nombre, $apellido, $nacionalidad, $fnNull);
        if ($stmt->execute()) {
            $stmt->close();
            $conn->close();
            header('Location: index.php?msg=created');
            exit;
        } else {
            $errors[] = 'Error al guardar: ' . $stmt->error;
            $stmt->close();
            $conn->close();
        }
    }
}

$pageTitle  = 'Nuevo Autor';
$activePage = 'autores';
require_once '../includes/header.php';
?>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Nuevo Autor</h1>
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

<div class="card shadow-sm" style="max-width:600px;">
    <div class="card-body">
        <form method="POST" action="crear.php" novalidate>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                <input type="text" id="nombre" name="nombre" class="form-control"
                       value="<?= htmlspecialchars($nombre) ?>" required>
            </div>
            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido <span class="text-danger">*</span></label>
                <input type="text" id="apellido" name="apellido" class="form-control"
                       value="<?= htmlspecialchars($apellido) ?>" required>
            </div>
            <div class="mb-3">
                <label for="nacionalidad" class="form-label">Nacionalidad</label>
                <input type="text" id="nacionalidad" name="nacionalidad" class="form-control"
                       value="<?= htmlspecialchars($nacionalidad) ?>">
            </div>
            <div class="mb-3">
                <label for="fecha_nacimiento" class="form-label">Fecha de Nacimiento</label>
                <input type="date" id="fecha_nacimiento" name="fecha_nacimiento" class="form-control"
                       value="<?= htmlspecialchars($fecha_nacimiento) ?>">
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

<?php require_once '../includes/footer.php'; ?>
