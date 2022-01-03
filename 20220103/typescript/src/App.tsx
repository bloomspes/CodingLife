export default function App() {
    return (
        <>
        <form>
            <li>
                <input
                    placeholder="문제의 답을 입력하세요."
                    onChange={handleEvent}
                    value={title}
                />
                <button>Submit</button>
            </li>
        </form>
        </>
    )
}