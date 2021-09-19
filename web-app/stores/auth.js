import {makeAutoObservable} from "mobx";
import api from "../helpers/axiosHelper";
import cookieHelper from "../helpers/cookieHelper";
import {fa} from "faker/lib/locales";

const cookieTokenKey = "token"
const remeberMeDuration = 2592000

class AuthStore {

    loggedIn = false
    user = undefined

    constructor() {
        makeAutoObservable(this)
    }

    login(userName, password, rememberMe, callback) {
        let data = {userName: userName, password: password}
        api.post("/auth/login/basic", data)
            .then((response) => {
                let user = response.user
                let token = response.token
                if(rememberMe) {
                    cookieHelper.setCookie(cookieTokenKey, token, remeberMeDuration)
                } else {
                    cookieHelper.setCookie(cookieTokenKey, token, -1)
                }
                this.user = user;
                this.loggedIn = true
                callback(true)
            }).catch((error) => {
                callback(error)
        })
    }

    update(callback) {
        if(cookieHelper.existsCookie(cookieTokenKey)) {
            let token = cookieHelper.getCookie(cookieTokenKey)
            let data = {token: token}
            api.post("/auth/login/token", data)
                .then((response) => {
                    this.user = response.user
                    this.loggedIn = true
                    callback(true)
                }).catch(() => {
                    callback(false)
            })
        } else {
            callback(false)
        }
    }

    logout() {
        if (cookieHelper.existsCookie(cookieTokenKey)) {
            cookieHelper.removeCookie(cookieTokenKey)
        }
        this.loggedIn = false
    }
}

const authStore = new AuthStore()
export default authStore