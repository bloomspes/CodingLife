import module

for r in module.query('poetry').results:
    print(r.url + ' - ' + r.text)