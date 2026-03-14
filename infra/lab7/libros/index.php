<?php
require_once '../config/database.php';
$pageTitle  = 'Libros';
$activePage = 'libros';
require_once '../includes/header.php';

$conn = getConnection();
$libros = $conn->query(
    "SELECT l.*, CONCAT(a.nombre, ' ', a.apellido) AS autor
     FROM libros l
     LEFT JOIN autores a ON l.id_autor = a.id
     ORDER BY l.id DESC"
);
$conn->close();

$msg = $_GET['msg'] ?? '';
?>

<?php if ($msg === 'created'): ?>
    <div class="alert alert-success alert-dismissible fade show">
        <i class="bi bi-check-circle-fill me-2"></i>Libro creado correctamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'updated'): ?>
    <div class="alert alert-success alert-dismissible fade show">
        <i class="bi bi-check-circle-fill me-2"></i>Libro actualizado correctamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'deleted'): ?>
    <div class="alert alert-warning alert-dismissible fade show">
        <i class="bi bi-trash-fill me-2"></i>Libro eliminado.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'error'): ?>
    <div class="alert alert-danger alert-dismissible fade show">
        <i class="bi bi-exclamation-triangle-fill me-2"></i>Ocurrió un error.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php endif; ?>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Libros</h1>
    <a href="crear.php" class="btn btn-success">
        <i class="bi bi-book me-1"></i>Nuevo Libro
    </a>
</div>

<div class="card shadow-sm">
    <div class="card-body p-0">
        <table class="table table-hover table-striped mb-0">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Título</th>
                    <th>ISBN</th>
                    <th>Año</th>
                    <th>Género</th>
                    <th>Autor</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
<?php if ($libros && $libros->num_rows > 0): ?>
<?php   while ($l = $libros->fetch_assoc()): ?>
                <tr>
                    <td><?= $l['id'] ?></td>
                    <td><?= htmlspecialchars($l['titulo']) ?></td>
                    <td><?= htmlspecialchars($l['isbn'] ?? '—') ?></td>
                    <td><?= htmlspecialchars($l['anio_publicacion'] ?? '—') ?></td>
                    <td><?= htmlspecialchars($l['genero'] ?? '—') ?></td>
                    <td><?= htmlspecialchars($l['autor'] ?? '—') ?></td>
                    <td class="text-center">
                        <a href="editar.php?id=<?= $l['id'] ?>" class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil-fill"></i>
                        </a>
                        <form method="POST" action="eliminar.php" class="d-inline"
                              onsubmit="return confirm('¿Eliminar el libro <?= htmlspecialchars(addslashes($l['titulo'])) ?>?')">
                            <input type="hidden" name="id" value="<?= $l['id'] ?>">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="bi bi-trash-fill"></i>
                            </button>
                        </form>
                    </td>
                </tr>
<?php   endwhile; ?>
<?php else: ?>
                <tr><td colspan="7" class="text-center text-muted">No hay libros registrados.</td></tr>
<?php endif; ?>
            </tbody>
        </table>
    </div>
</div>

<?php require_once '../includes/footer.php'; ?>
