import axios from 'axios/index'

export default class Api {

    static apiUrl = "http://localhost:8077/api/stocks/";

    static getStocksList() {
        return axios.get(this.apiUrl);
    }

    static createStock(request) {
        return axios.post(this.apiUrl, request);
    }

    static getStockById(id) {
        return axios.get(this.apiUrl + id);
    }

    static updateStock(id, request) {
        return axios.put(this.apiUrl + id, request)
    }
}
