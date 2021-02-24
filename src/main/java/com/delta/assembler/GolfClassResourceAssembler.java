package com.delta.assembler;

import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.delta.api.GolfClassController;
import com.delta.entity.GolfClass;
import com.delta.resource.GolfClassResource;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-23
 */
@Component
public class GolfClassResourceAssembler extends IdentifiableResourceAssemblerSupport<GolfClass, GolfClassResource> {

  public GolfClassResourceAssembler() {
    super(GolfClassController.class, GolfClassResource.class);
  }
  
  @Override
  public GolfClassResource toResource(GolfClass entity) {
    GolfClassResource resource = instantiateResource(entity);
    resource.setEntity(entity);
    return resource;
  }

}
