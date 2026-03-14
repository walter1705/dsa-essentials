<?php
require_once '../config/database.php';

$errors = [];
$id = (int)($_GET['id'] ?? $_POST['id'] ?? 0);

if ($id <= 0) {
    header('Location: index.php?msg=error');
    exit;
}

$conn = getConnection();

// Load existing record
$stmt = $conn->prepare("SELECT * FROM autores WHERE id = ?");
$stmt->bind_param('i', $id);
$stmt->execute();
$autor = $stmt->get_result()->fetch_assoc();
$stmt->close();

if (!$autor) {
    $conn->close();
    header('Location: index.php?msg=error');
    exit;
}

$nombre           = $autor['nombre'];
$apellido         = $autor['apellido'];
$nacionalidad     = $autor['nacionalidad'] ?? '';
$fecha_nacimiento = $autor['fecha_nacimiento'] ?? '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nombre           = trim($_POST['nombre']           ?? '');
    $apellido         = trim($_POST['apellido']         ?? '');
    $nacionalidad     = trim($_POST['nacionalidad']     ?? '');
    $fecha_nacimiento = trim($_POST['fecha_nacimiento'] ?? '');

    if ($nombre === '')   $errors[] = 'El nombre es obligatorio.';
    if ($apellido === '') $errors[] = 'El apellido es obligatorio.';

    if (empty($errors)) {
        $fnNull = $fecha_nacimiento !== '' ? $fecha_nacimiento : null;
        $stmt = $conn->prepare(
            "UPDATE autores SET nombre=?, apellido=?, nacionalidad=?, fecha_nacimiento=? WHERE id=?"
        );
        $stmt->bind_param('ssssi', $nombre, $apellido, $nacionalidad, $fnNull, $id);
        if ($stmt->execute()) {
            $stmt->close();
            $conn->close();
            header('Location: index.php?msg=updated');
            exit;
        } else {
            $errors[] = 'Error al actualizar: ' . $stmt->error;
            $stmt->close();
        }
    }
}

$conn->close();

$pageTitle  = 'Editar Autor';
$activePage = 'autores';
require_once '../includes/header.php';
?>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Editar Autor <small class="text-muted fs-5">#<?= $id ?></small></h1>
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
        <form method="POST" action="editar.php" novalidate>
            <input type="hidden" name="id" value="<?= $id ?>">
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
                <button type="submit" class="btn btn-warning">
                    <i class="bi bi-save-fill me-1"></i>Actualizar
                </button>
                <a href="index.php" class="btn btn-outline-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</div>

<?php require_once '../includes/footer.php'; ?>
