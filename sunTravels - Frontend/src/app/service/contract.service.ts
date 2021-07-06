import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Contract} from '../model/contract';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  private contractorsUrl: string;
  
  constructor(private http: HttpClient) {
    this.contractorsUrl = 'http://localhost:8090/contracts/addNewContract';
  }
  public findAll(): Observable<Contract[]> {
    return this.http.get<Contract[]>(this.contractorsUrl);
  }
  // tslint:disable-next-line:typedef
  public save(contract: Contract): Observable<any> {
    return this.http.post(this.contractorsUrl, contract);
  }
  createContract(contract: Contract): Observable<any> {
    return this.http.post('http://localhost:8090/contracts/addNewContract', contract);
  }
}
