<?php
require_once '../config/database.php';
$pageTitle  = 'Publicaciones';
$activePage = 'publicaciones';
require_once '../includes/header.php';

$conn = getConnection();
$publicaciones = $conn->query(
    "SELECT p.*, l.titulo AS libro_titulo
     FROM publicaciones p
     JOIN libros l ON p.id_libro = l.id
     ORDER BY p.id DESC"
);
$conn->close();

$msg = $_GET['msg'] ?? '';
?>

<?php if ($msg === 'created'): ?>
    <div class="alert alert-success alert-dismissible fade show">
        <i class="bi bi-check-circle-fill me-2"></i>Publicación creada correctamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'updated'): ?>
    <div class="alert alert-success alert-dismissible fade show">
        <i class="bi bi-check-circle-fill me-2"></i>Publicación actualizada correctamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'deleted'): ?>
    <div class="alert alert-warning alert-dismissible fade show">
        <i class="bi bi-trash-fill me-2"></i>Publicación eliminada.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php elseif ($msg === 'error'): ?>
    <div class="alert alert-danger alert-dismissible fade show">
        <i class="bi bi-exclamation-triangle-fill me-2"></i>Ocurrió un error.
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
<?php endif; ?>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h1>Publicaciones</h1>
    <a href="crear.php" class="btn btn-success">
        <i class="bi bi-journal-plus me-1"></i>Nueva Publicación
    </a>
</div>

<div class="card shadow-sm">
    <div class="card-body p-0">
        <table class="table table-hover table-striped mb-0">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Libro</th>
                    <th>Editorial</th>
                    <th>Fecha Publicación</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
<?php if ($publicaciones && $publicaciones->num_rows > 0): ?>
<?php   while ($p = $publicaciones->fetch_assoc()): ?>
                <tr>
                    <td><?= $p['id'] ?></td>
                    <td><?= htmlspecialchars($p['libro_titulo']) ?></td>
                    <td><?= htmlspecialchars($p['editorial']) ?></td>
                    <td><?= $p['fecha_publicacion'] ? htmlspecialchars($p['fecha_publicacion']) : '—' ?></td>
                    <td><?= $p['precio'] !== null ? '$' . number_format((float)$p['precio'], 2) : '—' ?></td>
                    <td><?= (int)$p['stock'] ?></td>
                    <td class="text-center">
                        <a href="editar.php?id=<?= $p['id'] ?>" class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil-fill"></i>
                        </a>
                        <form method="POST" action="eliminar.php" class="d-inline"
                              onsubmit="return confirm('¿Eliminar esta publicación?')">
                            <input type="hidden" name="id" value="<?= $p['id'] ?>">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="bi bi-trash-fill"></i>
                            </button>
                        </form>
                    </td>
                </tr>
<?php   endwhile; ?>
<?php else: ?>
                <tr><td colspan="7" class="text-center text-muted">No hay publicaciones registradas.</td></tr>
<?php endif; ?>
            </tbody>
        </table>
    </div>
</div>

<?php require_once '../includes/footer.php'; ?>
