<?php
require_once './config/database.php';
$pageTitle  = 'Inicio';
$activePage = 'inicio';
require_once './includes/header.php';

$conn = getConnection();

// Counts
$totalAutores      = $conn->query("SELECT COUNT(*) AS c FROM autores")->fetch_assoc()['c'];
$totalLibros       = $conn->query("SELECT COUNT(*) AS c FROM libros")->fetch_assoc()['c'];
$totalPublicaciones = $conn->query("SELECT COUNT(*) AS c FROM publicaciones")->fetch_assoc()['c'];

// Last 5 libros with author name
$ultimos = $conn->query(
    "SELECT l.id, l.titulo, l.anio_publicacion, l.genero,
            CONCAT(a.nombre, ' ', a.apellido) AS autor
     FROM libros l
     LEFT JOIN autores a ON l.id_autor = a.id
     ORDER BY l.id DESC
     LIMIT 5"
);
$conn->close();
?>

<h1 class="mb-4">Dashboard</h1>

<div class="row g-4 mb-5">
    <!-- Autores -->
    <div class="col-md-4">
        <a href="/lab7/autores/index.php" class="text-decoration-none">
            <div class="card card-stat text-white bg-primary shadow">
                <div class="card-body d-flex align-items-center gap-3">
                    <i class="bi bi-person-lines-fill fs-1"></i>
                    <div>
                        <div class="fs-1 fw-bold"><?= $totalAutores ?></div>
                        <div class="fs-5">Total Autores</div>
                    </div>
                </div>
            </div>
        </a>
    </div>
    <!-- Libros -->
    <div class="col-md-4">
        <a href="/lab7/libros/index.php" class="text-decoration-none">
            <div class="card card-stat text-white bg-success shadow">
                <div class="card-body d-flex align-items-center gap-3">
                    <i class="bi bi-book-fill fs-1"></i>
                    <div>
                        <div class="fs-1 fw-bold"><?= $totalLibros ?></div>
                        <div class="fs-5">Total Libros</div>
                    </div>
                </div>
            </div>
        </a>
    </div>
    <!-- Publicaciones -->
    <div class="col-md-4">
        <a href="/lab7/publicaciones/index.php" class="text-decoration-none">
            <div class="card card-stat text-white shadow" style="background-color:#fd7e14;">
                <div class="card-body d-flex align-items-center gap-3">
                    <i class="bi bi-journal-text fs-1"></i>
                    <div>
                        <div class="fs-1 fw-bold"><?= $totalPublicaciones ?></div>
                        <div class="fs-5">Total Publicaciones</div>
                    </div>
                </div>
            </div>
        </a>
    </div>
</div>

<div class="card shadow-sm">
    <div class="card-header bg-secondary text-white">
        <h5 class="mb-0"><i class="bi bi-clock-history me-2"></i>Últimos libros registrados</h5>
    </div>
    <div class="card-body p-0">
        <table class="table table-hover table-striped mb-0">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Título</th>
                    <th>Año</th>
                    <th>Género</th>
                    <th>Autor</th>
                </tr>
            </thead>
            <tbody>
<?php if ($ultimos && $ultimos->num_rows > 0): ?>
<?php   while ($row = $ultimos->fetch_assoc()): ?>
                <tr>
                    <td><?= $row['id'] ?></td>
                    <td><?= htmlspecialchars($row['titulo']) ?></td>
                    <td><?= htmlspecialchars($row['anio_publicacion']) ?></td>
                    <td><?= htmlspecialchars($row['genero']) ?></td>
                    <td><?= htmlspecialchars($row['autor'] ?? '—') ?></td>
                </tr>
<?php   endwhile; ?>
<?php else: ?>
                <tr><td colspan="5" class="text-center text-muted">Sin registros</td></tr>
<?php endif; ?>
            </tbody>
        </table>
    </div>
</div>

<?php require_once './includes/footer.php'; ?>
