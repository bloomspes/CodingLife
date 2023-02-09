// Use a map, reduce.

const solution = (progresses, speeds) => {

    const leftDays = progresses.map(
        (rates, day) => Math.ceil((100 - rates) / speeds[day])
        );

    const features = leftDays.reduce((deploy, lefts, index) => {
        const completes = deploy[deploy.length - 1];

        if (index > 0 && lefts <= completes[0]) {
            completes.push(lefts);
        }

        else {
            deploy.push([lefts]);
        }

        return deploy;
    }, []);

    return features.map(i => i.length);

};

test('solution', () => {
    expect(solution([93, 30, 55], [1, 30, 5])).toEqual([2, 1]);
    expect(solution([95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1])).toEqual([1, 3, 2]);
})


// Use a recursion and destructuring.

const solution2 = (progresses, speeds, deploys = []) => {
    if (progresses.length === 0) {
        return deploys;
    }

    const nextProgresses = features(progresses, speeds);
    const lastIndex = completes(progresses);

    if (lastIndex === -1) {
        return solution2(nextProgresses, speeds, deploys);
    }

    return solution2(
        nextProgresses.slice(lastIndex + 1),
        speeds.slice(lastIndex + 1),
        [...deploys, lastIndex + 1]
    );
}

const features = (progresses, speeds) =>
    progresses.map(
        (feature, day) => feature + speeds[day] > 100 ? 100 : feature + speeds[day]
    )


const completes = (progresses, index = -1) => {
    if (progresses[0] !== 100) {
        return index;
    }

    return completes(progresses.slice(1), index + 1);
}

const first = (arr) => arr[0];


test('solution2', () => {
    expect(solution2([93, 30, 55], [1, 30, 5])).toEqual([2, 1]);
    expect(solution2([95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1])).toEqual([1, 3, 2]);
})

describe('When the progress rate is less than 100', () => {
    it('returns counting a index', () => {
        expect(completes([93, 30, 55])).toBe(-1);
        expect(completes([100, 30, 55])).toBe(0);

        expect(completes([90, 30, 100])).toBe(-1);

        expect(completes([100, 100, 100])).toBe(2);
    })
})

