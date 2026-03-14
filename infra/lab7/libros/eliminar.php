<?php
require_once '../config/database.php';

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    header('Location: index.php');
    exit;
}

$id = (int)($_POST['id'] ?? 0);
if ($id <= 0) {
    header('Location: index.php?msg=error');
    exit;
}

$conn = getConnection();
$stmt = $conn->prepare("DELETE FROM libros WHERE id = ?");
$stmt->bind_param('i', $id);
if ($stmt->execute()) {
    $msg = 'deleted';
} else {
    $msg = 'error';
}
$stmt->close();
$conn->close();

header('Location: index.php?msg=' . $msg);
exit;
