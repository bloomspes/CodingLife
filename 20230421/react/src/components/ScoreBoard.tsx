import { Fragment, useState } from 'react';

import { frameRolls } from '../models/Game';

import useGame from '../hooks/useGame';

import { formatOrdinals, range } from '../utils';

export default function ScoreBoard() {
  const {
    game, frame, roll, addBalls,
  } = useGame();

  const [balls, setBalls] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      const value = parseInt(balls, 10);
      addBalls(value);
      setBalls('');
    } catch (e) {
      //
    }
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const value = parseInt(event.target.value, 10);
    setBalls(Math.min(value, 10).toString());
  };

  return (
    <div>
      <div>
        <table>
          <thead>
            <tr>
              {range(10).map((index) => (
                <th
                  key={index}
                  colSpan={frameRolls(index + 1)}
                  className={index + 1 === frame ? 'current' : ''}
                >
                  {index + 1}
                </th>
              ))}
              <th>
                Total
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              {range(10).map((index) => (
                <Fragment key={index}>
                  {range(frameRolls(index + 1)).map((rollIndex) => (
                    <td
                      key={rollIndex}
                      className={
                        index + 1 === frame && rollIndex + 1 === roll
                          ? 'current' : ''
                      }
                    >
                      {game.balls(index + 1, rollIndex + 1)}
                    </td>
                  ))}
                </Fragment>
              ))}
              <td rowSpan={2}>
                Total:
                {' '}
                {game.totalScore()}
              </td>
            </tr>
            <tr>
              {range(10).map((index) => (
                <td key={index} colSpan={frameRolls(index + 1)}>
                  {index + 1 < frame && (
                    <>{game.frameScore(index + 1)}</>
                  )}
                </td>
              ))}
            </tr>
          </tbody>
        </table>
      </div>
      {frame <= 10 ? (
        <div>
          <form onSubmit={handleSubmit}>
            <p>
              {formatOrdinals(frame)}
              {' '}
              frame
              {' '}
              {formatOrdinals(roll)}
              {' '}
              roll
            </p>
            <label htmlFor="input-balls">
              Balls
            </label>
            <input
              id="input-balls"
              type="number"
              value={balls}
              onChange={handleChange}
            />
            <button type="submit">
              Submit
            </button>
          </form>
        </div>
      ) : (
        <p>Game Over</p>
      )}
    </div>
  );
}
