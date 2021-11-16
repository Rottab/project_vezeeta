import {Component, OnDestroy, OnInit} from '@angular/core';
import {CategoryService} from "./category.service";
import {Category} from "./category.model";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit, OnDestroy {

  categoryList: Category[] = []
  private categorySubscription !: Subscription;

  constructor(private categoryService: CategoryService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.categorySubscription = this.categoryService
      .getCategories()
      .subscribe(
        response => this.categoryList = response
        , error => {
          this.router.navigate(['error'])
        }
      )
  }

  ngOnDestroy(): void {
    if (this.categorySubscription)
      this.categorySubscription.unsubscribe();
  }

  onCategory(id: number) {
    this.router.navigate(['doctors'], {queryParams: {categoryId: id}})
  }
}
