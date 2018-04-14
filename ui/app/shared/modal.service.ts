import { Injectable } from '@angular/core';
import { Overlay, OverlayConfig, OverlayRef } from "@angular/cdk/overlay";
import { ComponentPortal, ComponentType } from "@angular/cdk/portal";

@Injectable()
export class ModalService {

  private overlayRef: OverlayRef;

  private portal: ComponentPortal<any>;

  constructor(private overlay: Overlay) { }

  public open(componentClass: ComponentType<any>): void {
    const config = this.getConfig();
    this.overlayRef = this.overlay.create(config);
    this.overlayRef.backdropClick().subscribe(() => this.overlayRef.detach());

    this.portal = new ComponentPortal(componentClass);
    this.portal.attach(this.overlayRef);
  }

  private getConfig(): OverlayConfig {
    const positionStrategy = this.overlay.position()
      .global()
      .centerHorizontally()
      .centerVertically();

    const config = new OverlayConfig();
    config.positionStrategy = positionStrategy;
    config.width = '600px';
    config.height = '338px';
    config.hasBackdrop = true;
    config.backdropClass = 'backdrop';

    return config;
  }

}
