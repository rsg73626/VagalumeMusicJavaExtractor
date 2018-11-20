from pathlib import Path
from sklearn.feature_extraction.text import CountVectorizer

### READING FILES
p = Path('./2 - English')

allMusicLyrics = []

allMusicFiles = list(p.glob('**/*.txt'))

for file in allMusicFiles:
    with file.open() as f:
        allMusicLyrics.append(f.read().replace('\n', ' '))
        f.close()

### DOING BAG OF WORDS PROCESS
vectorizer = CountVectorizer()
bagOfWordsResult = vectorizer.fit_transform(allMusicLyrics)

print(vectorizer.get_feature_names())
