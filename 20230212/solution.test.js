const context = describe;

class Alphabet {
    constructor({ excludes }) {
      this.charSet = [...'abcdefghijklmnopqrstuvwxyz']
        .filter((char) => !excludes.includes(char));
    }

    jump(char, offset) {
      const index = this.charSet.indexOf(char);
      const newIndex = (index + offset) % this.charSet.length;
      return this.charSet[newIndex];
    }
  }


// const remove = (skip) => {
//   const alphabets = [...'abcdefghijklmnopqrstuvwxyz'];
//   return alphabets.filter((char) => !skip.includes(char));
// };

// const jump = (char, offset, charSet) => {
//   const index = charSet.indexOf(char);
//   const newIndex = (index + offset) % charSet.length;
//   return charSet[newIndex];
// };

const encode = (string, skip, offset) => {
  // const charSet = remove(skip);

  const charSet = new Alphabet({ excludes: skip })

  return [...string]
    .map((c) => charSet.jump(c, offset))
    .join('');
};

test('sample', () => {
  expect(encode('aukks', 'wbqd', 5)).toBe('happy');
});

describe('remove', () => {
  it('returns without skip', () => {
    expect(remove('abcdefghijklmnopqrstuvwx')).toEqual(['y', 'z']);
  });
});

describe('jump', () => {
  it('returns alphabet with offset', () => {
    expect(jump('a', 1, 'abc')).toBe('b');
  });

  context('when offset is larger than character set length.', () => {
    it('returns alphabet with offset', () => {
      expect(jump('a', 3, 'abc')).toBe('a');
    });
  });
});
