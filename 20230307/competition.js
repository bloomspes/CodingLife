// #. 문제분석
// ##. 구하는 것
// - 라이언(이전 대회 우승자)이 가장 큰 점수차이로 우승하려면 어떤 점수 과녁에 맞춰야 하는지
// ##. 주어진 것
// - 라이언이 쏠 수 있는 화살 개수
// - 어피치(파트너)가 맞힌 과녁 점수 배열

// #. 조건

// ##. 과녁 점수 계산
// - '과녁'에 '화살'을 더 많이 맞힌 사람이 과녁점수 만큼 점수를 가져감.
//  ex) 10 점 과녁, 라이언 3, 어피치 2 => 라이언 10점
// - 과녁에 맞힌 화살 개수가 같을때, 라이언이 우승자이므로 어피치가 점수를 가져감.
// - 과녁에 맞힌 화살 개수가 0일때, 어느쪽도 점수를 가져가지 않음.

// ##. 최종점수
// - 최종점수가 같은 경우, 어피치가 우승

// # 예시.
// 화살개수: 5개, 어피치: [2,1,1,1,0,0,0,0,0,0,0]
// => [3, 2]
// - 같은 과녁일 경우, 어피치 보다 한발 많아야함.
// - 어느 과녁에서 이길지는 경우의 수.
//  - 화살이 5개라면, 점수가 가장 큰 과녁부터 5개의 과녁만 고려하면 됨.

// 어피치를 이긴다 => [3, 2, 2, 2, 1, 1, 1, 1, 1, 1]
// 10, 9 => 19
// 9, 8, 6 => 23
// 8, 7, 6 => 21
// 화살을 각 과녁에 분배하는 경우의 수 => 그 중에 합 최대.

// [19
// 10에서 이긴다 => 10, 9
// 10에서 진다
//   - 9에서 이긴다 => 9, 8, 6
//   - 9에서 진다 =>

// DP 문제 => 점화식
// maxScore(남은화살개수, 남은것중 가장높은 과녁점수)
// ex) maxScore(5, 10) = max(maxScore(2, 9), maxScore(5, 9))
// maxScore(arrows, target) = max(
//  maxScore(arrows - (appeachScore + 1), target - 1),
//  maxScore(arrows, target - 1)
// )

// 화살이 5개, 점수는 내림차순.
// 10 - 6 까지의 수로 점수 합을 조합하면 ==> 가장 큰 수가 나오겠지? 라이언 점수 - 어피치 점수 이 값이 가장 크려면
// 라이언 점수를 가장 많이 얻을 수 있는 과녁을 선택 해야 함.
// 과녁의 레인지는 내림차순임. 화살이 최대한 많은 수를 선택할 수 있도록 과녁을 잡는다면 [10, 9, 8, 7, 6]
// 점수 합산 => 과녁 점수의 합 0에서 10까지의 수가 있음, 5개를 뽑아서 최대를 만든다고 하면

// 어피치가 획득한 점수 = [10, 9, 8, 7]

// [3, 2, 0, 0, 0, 0, 0, 0, 0, 0]
// 점수차이 합산 => 10 + 9 -

function calculateScoreDifference(scores, appeachScores) {
  return scores.reduce((totalScore, lionScore, index) => {
    if (lionScore === 0 && appeachScores[index] > 0) {
      return totalScore - (10 - index);
    }

    if (lionScore > 0) {
      return totalScore + 10 - index;
    }

    return totalScore;
  }, 0);
}

function maxNonZeroIndex(numbers) {
  return numbers.length - [...numbers].reverse().findIndex((n) => n !== 0) - 1;
}

function solution({ n, appeachScores }) {
  const shoots = [];

  function shot({ arrows, status }) {
    if (arrows < 0 || status.length > 11) {
      return;
    }

    if (arrows === 0) {
      const trailingZeros = Array(11 - status.length).fill(0);

      shoots.push({
        totalScore: calculateScoreDifference(
          [...status, ...trailingZeros],
          appeachScores,
        ),
        status: [...status, ...trailingZeros],
      });

      return;
    }

    // 지금과녁이 0일때
    if (status.length === 10) {
      // 나머지 화살 다 쏜다!!
      shoots.push({
        totalScore: calculateScoreDifference(
          [...status, arrows],
          appeachScores,
        ),
        status: [...status, arrows],
      });
      return;
    }

    // 어피치를 이긴다.
    const shotCount = (appeachScores[status.length]) + 1;
    shot({
      arrows: arrows - shotCount,
      status: [...status, shotCount],
    });
    // 이번엔 진다.
    shot({
      arrows,
      status: [...status, 0],
    });
  }

  shot({ arrows: n, status: [] }); // 사격하면서 화살이 남지 않으면 기록

  const [bestShoot] = shoots
    .sort((a, b) => maxNonZeroIndex(b.status) - maxNonZeroIndex(a.status)) // 가장 낮은 점수를 더 많이 맞췄다.
    .sort((a, b) => b.totalScore - a.totalScore);

  // 라이언 못이김, 비길수도
  if (bestShoot.totalScore <= 0) {
    return [-1];
  }

  return bestShoot.status;
}

test('calculateScoreDifference', () => {
  expect(calculateScoreDifference(
    [3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0],
  )).toBe(4);
  expect(calculateScoreDifference(
    [0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0],
    [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0],
  )).toBe(6);
});

test('maxNonZeroIndex', () => {
  expect(maxNonZeroIndex([2, 0, 2, 3, 0, 1, 0, 0, 0, 0, 0])).toBe(5);
  expect(maxNonZeroIndex([2, 0, 2, 3, 0, 1, 1, 0, 0, 0, 0])).toBe(6);
  expect(maxNonZeroIndex([2, 0, 2, 3, 0, 1, 0, 0, 0, 5, 0])).toBe(9);
});

test('sample', () => {
  expect(solution({
    n: 5,
    appeachScores: [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0],
  }))
    .toEqual([0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0]);

  expect(solution({
    n: 9,
    appeachScores: [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1],
  }))
    .toEqual([1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0]);

  expect(solution({
    n: 10,
    appeachScores: [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3],
  }))
    .toEqual([1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2]);

  expect(solution({
    n: 1,
    appeachScores: [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
  }))
    .toEqual([-1]);
});


// #. Bad.
// - shot 을 테스트 할 수 없었던게 패착. 가장 큰 패배 요인.
//  => 디버깅에 엄청난 시간.
//  => 무지성으로 막 결과를 여러개 프린트해봄. => 이걸 안할 수 있다면
//  => 이러면 혼이 나간다, 포기하게 된다

// #. Good.
// - 최소한 기도메타로 코드 수정은 안함.
// - tricky 한 로직은 별도의 함수로 분리후 테스트함.
