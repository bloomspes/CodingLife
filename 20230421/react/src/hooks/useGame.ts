import { useRef, useState } from 'react';

import Game, { frameRolls, isLastFrame } from '../models/Game';

function step({
  game, frame, roll, balls,
}: {
    game: Game;
    frame: number;
    roll: number;
    balls: number;
  }) {
  game.roll({ frame, balls });

  if ((!isLastFrame(frame) && balls === 10) || roll === frameRolls(frame)) {
    return {
      frame: frame + 1,
      roll: 1,
    };
  }

  return {
    frame,
    roll: roll + 1,
  };
}

export default function useGame() {
  const refGame = useRef(new Game());
  const game = refGame.current;

  const [frame, setFrame] = useState(1);
  const [roll, setRoll] = useState(1);

  const addBalls = (balls: number) => {
    if (!Number.isInteger(balls)) {
      throw Error('Invalid input');
    }

    const { frame: nextFrame, roll: nextRoll } = step({
      game, frame, roll, balls,
    });

    setFrame(nextFrame);
    setRoll(nextRoll);
  };

  return {
    game, frame, roll, addBalls,
  };
}
