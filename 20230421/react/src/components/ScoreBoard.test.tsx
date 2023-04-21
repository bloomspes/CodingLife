import { render, screen, fireEvent } from '@testing-library/react';

import ScoreBoard from './ScoreBoard';

describe('ScoreBoard', () => {
  it('renders frames and scores', () => {
    render(<ScoreBoard />);

    screen.getByText('1st frame 1st roll');

    fireEvent.change(screen.getByLabelText('Balls'), {
      target: { value: 0 },
    });

    fireEvent.click(screen.getByText('Submit'));

    screen.getByText('Total: 0');

    // screen.getByText('1th frame 2nd roll');

    // fireEvent.change(screen.getByLabelText('Balls'), {
    //   target: { value: 1 },
    // });

    // screen.getByText('Total: 1');

    // screen.getByText('2nd frame 1st roll');

    // fireEvent.change(screen.getByLabelText('Balls'), {
    //   target: { value: 10 },
    // });

    // screen.getByText('Total: 11');
  });
});
