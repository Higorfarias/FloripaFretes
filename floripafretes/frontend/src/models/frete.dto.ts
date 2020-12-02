import { DateTimeData } from "ionic-angular/umd/util/datetime-util";

export interface FreteDTO {
    id : string,
    titulo : string,
    descricao : string,
    endereco : string,
    data : DateTimeData,
    cep : string,
    cel : string;

}