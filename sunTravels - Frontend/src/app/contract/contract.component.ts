import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { ContractService } from '../service/contract.service';
import { Contract } from '../model/contract';


@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent implements OnInit {
  submitted = false;
  addForm: FormGroup;
  contract: Contract;
  //contactNumbers = new FormArray([]);

  // tslint:disable-next-line:typedef
  addContacts() {
    /*let contactArray = this.addForm.controls.contactNoList as FormArray;
    let arraylen = contactArray.length;

    let newContactgroup: FormGroup = this.fb.group({
      contactNo: ['', Validators.required]
    })

    contactArray.insert(arraylen, newContactgroup);*/
    
    this.contactNoList.push(new FormControl(''));
  }
addRoomTypes(){
  const newRoomTypegroup: FormGroup = this.fb.group({
    type: ['', [Validators.required]],
    noOfRooms: ['', [Validators.required]],
    noOfAdults: ['', [Validators.required]],
    price: ['', [Validators.required]]
  });
  this.roomTypeList.insert(this.roomTypeList.length, newRoomTypegroup);
}
  // tslint:disable-next-line:typedef


  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.addForm = this.fb.group({
      hotelName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      // tslint:disable-next-line:max-line-length
      contactNoList: this.fb.array([[ '', [Validators.required, Validators.pattern('^[0-9]+$')]]]),
      roomTypeList: this.fb.array([
        this.fb.group({
      type: ['', [Validators.required]],
      noOfRooms: ['', [Validators.required]],
      noOfAdults: ['', [Validators.required]],
      price: ['', [Validators.required]]
    })]),
      startDate: ['', [Validators.required]],
      endDate: ['', [Validators.required]],
      markup: ['', [Validators.required]],
      address: ['', [Validators.required]],
      acceptTerms: [false, Validators.requiredTrue]
    });
  }


  constructor(
    public fb: FormBuilder,
    private router: Router,
    private contractService: ContractService) {
    this.contract = {
      hotelName: '',
      email: '',
      contactNoList: null,
      type: '',
      noOfRooms: null,
      noOfAdults: null,
      price: null,
      endDate: null,
      startDate: null,
      markup: null,
      address: ' '

    };
  }

  // tslaint:disable-next-line:typedef
  get f() { return this.addForm.controls; }

 get contactNoList(): FormArray {
    return this.addForm.get('contactNoList') as FormArray;
  }
  get roomTypeList(): FormArray {
    return this.addForm.get('roomTypeList') as FormArray;
  }
  // tslint:disable-next-line:typedef
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.addForm.invalid) {
      return;
    }
    /*this.contract.hotelName = this.addForm.get('hotelName').value;
    this.contract.email = this.addForm.get('email').value;
    this.contract.contactNo = this.addForm.get('contactNo').value;
    this.contract.roomType = this.addForm.get('roomType').value;
    this.contract.noOfRooms = this.addForm.get('noOfRooms').value;
    this.contract.address = this.addForm.get('address').value;
    this.contract.startDate = this.addForm.get('startDate').value;
    this.contract.endDate = this.addForm.get('endDate').value;
    this.contract.noOfAdults = this.addForm.get('noOfAdults').value;
    this.contract.price = this.addForm.get('price').value;
    this.contract.markup = this.addForm.get('markup').value;*/
    //this.contractService.save(this.contract).subscribe((data) => this.gotoContractList());



    // display form values on success
    alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.addForm.value, null, 4));

    this.contractService.save(this.addForm.value)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        // tslint:disable-next-line:no-shadowed-variable
        error => { console.log(error); });
  }
  // tslint:disable-next-line:typedef
  gotoContractList() {
    this.router.navigate(['/']);
  }
  // tslint:disable-next-line:typedef
  onReset() {
    this.submitted = false;
    this.addForm.reset();
  }

}
