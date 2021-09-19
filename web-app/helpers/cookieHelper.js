import Cookies from "universal-cookie"
const cookies = new Cookies();

class CookieHelper {
    removeCookie(key) {
        cookies.remove(key, { path: '/' })
    }

    setCookie(key, value, age = -1) {
        if (age == -1) {
            cookies.set(key, value, {path: "/"})
            return;
        }

        cookies.set(key, value, {path: "/", maxAge: age})
    }

    existsCookie(key) {
        let cookie = cookies.get(key);
        return !!cookie;
    }

    getCookie(key) {
        return cookies.get(key);
    }
}

const cookieHelper = new CookieHelper();
export default cookieHelper;