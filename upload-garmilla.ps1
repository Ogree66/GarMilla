# upload-garmilla.ps1
# GarMilla projekt feltöltése GitHubra

# 1. Lépjünk a projekt mappába
cd "C:\Dev\GarMilla"

# 2. Biztosítsuk a remote URL-t
git remote set-url origin https://github.com/Ogree66/GarMilla.git

# 3. Branch neve legyen main
git branch -M main

# 4. Hozzáadjuk az összes fájlt
git add .

# 5. Commit (ha még nincs)
git commit -m "Automatikus feltöltés - GarMilla" || echo "Már van commit"

# 6. Feltöltés a GitHubra
git push -u origin main --force
