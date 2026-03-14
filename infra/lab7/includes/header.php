<?php
// $pageTitle and $activePage should be set before including this file
$pageTitle  = $pageTitle  ?? 'Editorial DB';
$activePage = $activePage ?? '';

$navLinks = [
    'inicio'       => ['href' => '/lab7/index.php',               'label' => 'Inicio'],
    'autores'      => ['href' => '/lab7/autores/index.php',        'label' => 'Autores'],
    'libros'       => ['href' => '/lab7/libros/index.php',         'label' => 'Libros'],
    'publicaciones'=> ['href' => '/lab7/publicaciones/index.php',  'label' => 'Publicaciones'],
];
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?= htmlspecialchars($pageTitle) ?> — Editorial DB</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body { background-color: #f8f9fa; }
        .navbar-brand { font-size: 1.3rem; }
        .card-stat { transition: transform .2s; }
        .card-stat:hover { transform: translateY(-4px); }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand fw-bold" href="/lab7/index.php">📚 Editorial DB</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMain">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navMain">
            <ul class="navbar-nav ms-auto">
<?php foreach ($navLinks as $key => $link): ?>
                <li class="nav-item">
                    <a class="nav-link<?= ($activePage === $key) ? ' active fw-semibold' : '' ?>"
                       href="<?= $link['href'] ?>"><?= $link['label'] ?></a>
                </li>
<?php endforeach; ?>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-4">
