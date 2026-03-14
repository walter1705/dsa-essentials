<?php
require_once '../config/database.php';
$pageTitle  = 'Autores';
$activePage = 'autores';
require_once '../includes/header.php';

$conn = getConnection();
$autores = $conn->query("SELECT * FROM autores ORDER BY apellido, nombre");
$conn->close();

$msg = $_GET['msg'] ?? '';
?>

<?php if ($msg === 'created'): ?>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi bi-check-circle-fill me-2"></i>Autor creado correctamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'updated'): ?>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi bi-check-circle-fill me-2"></i>Autor actualizado correctamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'deleted'): ?>
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <i class="bi bi-trash-fill me-2"></i>Autor eliminado.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'error'): ?>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2"></i>Ocurrió un error.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php endif; ?>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Autores</h1>
    <a href="crear.php" class="btn btn-success">
        <i class="bi bi-person-plus-fill me-1"></i>Nuevo Autor
    </a>
</div>

<div class="card shadow-sm">
    <div class="card-body p-0">
        <table class="table table-hover table-striped mb-0">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Nacionalidad</th>
                    <th>Fecha Nacimiento</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
<?php if ($autores && $autores->num_rows > 0): ?>
<?php   while ($a = $autores->fetch_assoc()): ?>
                <tr>
                    <td><?= $a['id'] ?></td>
                    <td><?= htmlspecialchars($a['nombre']) ?></td>
                    <td><?= htmlspecialchars($a['apellido']) ?></td>
                    <td><?= htmlspecialchars($a['nacionalidad'] ?? '—') ?></td>
                    <td><?= $a['fecha_nacimiento'] ? htmlspecialchars($a['fecha_nacimiento']) : '—' ?></td>
                    <td class="text-center">
                        <a href="editar.php?id=<?= $a['id'] ?>" class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil-fill"></i>
                        </a>
                        <form method="POST" action="eliminar.php" class="d-inline"
                              onsubmit="return confirm('¿Eliminar al autor <?= htmlspecialchars(addslashes($a['nombre'].' '.$a['apellido'])) ?>?')">
                            <input type="hidden" name="id" value="<?= $a['id'] ?>">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="bi bi-trash-fill"></i>
                            </button>
                        </form>
                    </td>
                </tr>
<?php   endwhile; ?>
<?php else: ?>
                <tr><td colspan="6" class="text-center text-muted">No hay autores registrados.</td></tr>
<?php endif; ?>
            </tbody>
        </table>
    </div>
</div>

<?php require_once '../includes/footer.php'; ?>
