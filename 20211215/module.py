import requests

params = dict(q='poetry', format='json')
parsed = requests.get('https://python-poetry.org/', params=params).json()

results = parsed['search']

def refactor(results):
    for r in results:
        if 'text' in r:
            print(r['firstURL'] + ' - ' + r['text'])

refactor(results)