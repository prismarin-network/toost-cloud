import {makeAutoObservable} from "mobx";

class PageStore {

    darkTheme = false;
    page = "home";

    constructor() {
        makeAutoObservable(this)
    }

    toggleTheme() {
        this.darkTheme = !this.darkTheme
    }

    switchPage(page) {
        this.page = page
    }
}

const pageStore = new PageStore()
export default pageStore