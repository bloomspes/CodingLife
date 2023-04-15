const PINS = 10;

function range(size) {
    return [...Array(size)].map((_, i) => i);
}

function sum(numbers) {
    return numbers.reduce((a, b) => a + b, 0);
}

function isStrike(frame) {
    return frame[0] === PINS;
}

function isSpare(frame) {
    return !isStrike(frame) && (frame[0] + frame[1]) === PINS;
}

function isLastFrame(frames, index) {
    return index + 1 >= frames.length;
}

function nextBalls1(frames, index) {
    if (isStrike(frames[index])) {
        if (isLastFrame(frames, index)) {
            return frames[index].splice(1);
        }
        if (frames[index + 1].length === 1) {
            return [frames[index + 1][0], frames[index + 2][0]];
        }
        return frames[index + 1].slice(0, 2);
    }

    if (isSpare(frames[index])) {
        return isLastFrame(frames, index)
            ? frames[index].splice(2)
            : frames[index + 1].slice(0, 1);
    }

    return [];
}

function nextBalls2(frames, index) {
    const balls = (skip, take) => [
        ...frames[index].slice(skip),
        ...(frames[index + 1] ?? []),
        ...(frames[index + 2] ?? []),
    ].slice(0, take);

    const frame = frames[index];

    return isStrike(frame)
        ? balls(1, 2)
        : (isSpare(frame) ? balls(2, 1) : []);
}

function nextBalls3(frames, index) {
    const balls = (skip, take) => {
        const step = (frame, index, count) => {
            const taken = frame.slice(0, count);
            const diff = count - taken.length;
            return diff === 0
                ? taken
                : [...taken, ...step(frames[index + 1], index + 1, diff)];
        };
        return step(frames[index].slice(skip), index, take);
    };

    const frame = frames[index];

    return isStrike(frame)
        ? balls(1, 2)
        : (isSpare(frame) ? balls(2, 1) : []);
}

function nextBalls4(frames, index) {
    const next = ([y, x]) => {
        return x + 1 < frames[y].length
            ? [y, x + 1]
            : [y + 1, 0];
    };

    const balls = (last, size) => range(size).reduce(({ balls, position }) => {
        const [y, x] = next(position);
        return {
            balls: [...balls, frames[y][x]],
            position: [y, x],
        };
    }, { balls: [], position: [index, last] }).balls;

    if (isStrike(frames[index])) {
        return balls(0, 2);
    }
    if (isSpare(frames[index])) {
        return balls(1, 1);
    }
    return [];
}

function randomInt(max) {
    return Math.floor(Math.random() * max);
}

function bonus(frames, index) {
    const functions = [nextBalls1, nextBalls2, nextBalls3, nextBalls4];
    const nextBalls = functions[randomInt(functions.length)];
    return sum(nextBalls(frames, index));
}

function frameScore(frames, index) {
    const frame = frames[index];
    const pins = (isStrike(frame) || isSpare(frame)) ? PINS : sum(frame);
    return pins + bonus(frames, index);
}

function score(frames) {
    return range(frames.length)
        .reduce((acc, index) => acc + frameScore(frames, index), 0);
}

module.exports = {
    score,
    nextBalls1,
    nextBalls2,
    nextBalls3,
    nextBalls4,
    sum,
};
