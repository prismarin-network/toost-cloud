export const ExampleComponent = ({ color, size, label, testFunction, ...props }) => {
    return (
        <div>
            <button onClick={testFunction} className={size} style={color && { color }}>{ label }</button>
        </div>
    )
}