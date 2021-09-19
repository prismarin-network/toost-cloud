import pageStore from "../stores/page";

// Describe what the test does
describe('Page Store', () => {
    it('Switches the page', () => {
        // Implement logic for the test
        pageStore.switchPage("Test")

        // Set the expected result
        expect(pageStore.page).toBe("Test")
    })
})
