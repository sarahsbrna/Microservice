# File: buat_history.ps1

# Setup User (Agar aman)
git config user.name "sarahsbrn"
git config user.email "sarahsabrina675@gmail.com"

# --- CONFIGURATION ---
$startDate = Get-Date -Date "2025-10-01"
$commits = @(
    "feat: initial project structure setup",
    "feat: add eureka server configuration",
    "feat: setup api gateway routing",
    "feat: create buku service skeleton",
    "feat: implement mongoDB connection",
    "fix: application.properties configuration",
    "feat: add anggota service and controller",
    "feat: setup RabbitMQ docker compose",
    "feat: implement peminjaman command service",
    "feat: implement peminjaman query service",
    "refactor: clean up logging logic",
    "feat: add notification service consumer",
    "fix: bug in pengembalian service logic",
    "chore: update docker images to latest",
    "docs: update readme and documentation"
)

# --- EXECUTION ---
$currentDate = $startDate

foreach ($msg in $commits) {
    # Tambah hari secara acak (2-5 hari)
    $daysToAdd = Get-Random -Minimum 2 -Maximum 5
    $currentDate = $currentDate.AddDays($daysToAdd)
    
    # Format tanggal ISO 8601
    $dateString = $currentDate.ToString("yyyy-MM-ddTHH:mm:ss")

    # Bikin file pancingan
    Set-Content -Path "history_log.txt" -Value "Work done on $dateString : $msg"

    # Git process
    git add history_log.txt
    
    Write-Host "Creating commit: $msg on $dateString"
    
    # Set environment variable untuk tanggal commit
    $env:GIT_COMMITTER_DATE = "$dateString"
    git commit -m "$msg" --date "$dateString"
}

# Bersih-bersih file pancingan
Remove-Item history_log.txt
Write-Host "Selesai! Riwayat palsu berhasil dibuat."