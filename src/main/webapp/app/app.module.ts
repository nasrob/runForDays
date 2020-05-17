import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { RunForDaysSharedModule } from 'app/shared/shared.module';
import { RunForDaysCoreModule } from 'app/core/core.module';
import { RunForDaysAppRoutingModule } from './app-routing.module';
import { RunForDaysHomeModule } from './home/home.module';
import { RunForDaysEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    RunForDaysSharedModule,
    RunForDaysCoreModule,
    RunForDaysHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    RunForDaysEntityModule,
    RunForDaysAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class RunForDaysAppModule {}
