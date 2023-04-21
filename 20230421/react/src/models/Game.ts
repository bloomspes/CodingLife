import { range, sum } from '../utils';

const FRAMES = 10;
const PINS = 10;

function isStrike(frame: number[]) {
  return frame[0] === PINS;
}

function isSpare(frame: number[]) {
  return !isStrike(frame) && (frame[0] + frame[1]) === PINS;
}

export function nextBalls(frames: number[][], index: number) {
  const balls = (skip: number, take: number) => [
    ...frames[index].slice(skip),
    ...(frames[index + 1] ?? []),
    ...(frames[index + 2] ?? []),
  ].slice(0, take);

  const frame = frames[index];

  if (isStrike(frame)) {
    return balls(1, 2);
  }
  return isSpare(frame) ? balls(2, 1) : [];
}

function bonus(frames: number[][], index: number) {
  return sum(nextBalls(frames, index));
}

function frameScore(frames: number[][], index: number) {
  const frame = frames[index];
  const pins = (isStrike(frame) || isSpare(frame)) ? PINS : sum(frame);
  return pins + bonus(frames, index);
}

export function score(frames: number[][]) {
  return range(frames.length)
    .reduce((acc, index) => acc + frameScore(frames, index), 0);
}

//

export function isLastFrame(frame: number) {
  return frame === 10;
}

export function frameRolls(frame: number) {
  return isLastFrame(frame) ? 3 : 2;
}

export default class Game {
  private frames: number[][] = range(FRAMES).map(() => []);

  roll({ frame, balls } : {
    frame: number;
    balls: number;
  }) {
    this.frames[frame - 1].push(balls);
  }

  balls(frame: number, roll: number) {
    return this.frames[frame - 1][roll - 1];
  }

  frameScore(frame: number): number {
    return frameScore(this.frames, frame - 1);
  }

  totalScore() {
    return score(this.frames);
  }
}
