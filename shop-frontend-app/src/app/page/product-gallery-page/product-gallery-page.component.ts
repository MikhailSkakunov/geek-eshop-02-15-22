import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {Page} from "../../model/page";

@Component({
  selector: 'app-product-gallery-page',
  templateUrl: './product-gallery-page.component.html',
  styleUrls: ['./product-gallery-page.component.scss']
})
export class ProductGalleryPageComponent implements OnInit {

  products: Product[] = [];

  page?: Page;

  nameFilter?: string;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.findAll().subscribe(res => {
      console.log("Loading products");
      this.products = res.content;
      this.page = res;
    },err => {
      console.log(`Error loading products ${err}`);
    });
  }

  goToPage(page: number) {
    this.productService.findAll(this.nameFilter, page).subscribe(res => {
      console.log("Loading products");
      this.products = res.content;
      this.page = res;
    },err => {
      console.log(`Error loading products ${err}`);
    });
  }

  searchNameEvent($event: string) {
    this.productService.findAll($event, 1).subscribe(res => {
      this.products = res.content;
      this.page = res;
      this.nameFilter = $event;
    }, err => {
      console.log(`Error loading products ${err}`)
    });
  }
}
