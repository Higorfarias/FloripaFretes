import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Rx";
import { API_CONFIG } from "../../config/api.config";
import { FreteDTO } from "../../models/frete.dto";

@Injectable()
export class FreteService {

    constructor(public http: HttpClient) {

    }

    findAll() : Observable<FreteDTO[]> {
        return this.http.get<FreteDTO[]>(`${API_CONFIG.baseUrl}/fretes`);
    }

}