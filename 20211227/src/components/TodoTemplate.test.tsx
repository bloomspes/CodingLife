import { render } from "@testing-library/react";

import TodoTemplate from "./TodoTemplate";

describe('TodoTemplate', () => {
    it('Todo 템플릿 생성하기', () => {
        const { container } = render(<TodoTemplate children="Todo 앱을 만들자!" />);

        expect(container).toHaveTextContent('Todo 앱을 만들자!');
    });
});