import { sum, formatOrdinals } from './utils';

test('sum', () => {
  expect(sum([2, 5])).toBe(7);
  expect(sum([2, 5, 7])).toBe(14);
  expect(sum([])).toBe(0);
});

test('ordinalNumber', () => {
  expect(formatOrdinals(0)).toBe('0th');
  expect(formatOrdinals(1)).toBe('1st');
  expect(formatOrdinals(2)).toBe('2nd');
  expect(formatOrdinals(3)).toBe('3rd');
  expect(formatOrdinals(4)).toBe('4th');

  expect(formatOrdinals(11)).toBe('11th');
  expect(formatOrdinals(21)).toBe('21st');
  expect(formatOrdinals(42)).toBe('42nd');

  expect(formatOrdinals(103)).toBe('103rd');
});
