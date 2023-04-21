export function range(size: number) {
  return [...Array(size)].map((_, i) => i);
}

export function sum(numbers: number[]) {
  return numbers.reduce((a, b) => a + b, 0);
}

// 출처: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/PluralRules/PluralRules

const pluralRules = new Intl.PluralRules('en-US', { type: 'ordinal' });

const suffixes = new Map([
  ['one', 'st'],
  ['two', 'nd'],
  ['few', 'rd'],
  ['other', 'th'],
]);

export function formatOrdinals(n: number) {
  const rule = pluralRules.select(n);
  const suffix = suffixes.get(rule);
  return `${n}${suffix}`;
}
