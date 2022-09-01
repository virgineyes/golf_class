package com.delta.assembler;

import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.delta.api.TitleController;
import com.delta.entity.Title;
import com.delta.resource.TitleResource;

/**
 * @author: ACE.CHIU
 * @create: 2022-09-01
 */
@Component
public class TitleResourceAssembler extends IdentifiableResourceAssemblerSupport<Title, TitleResource> {

  public TitleResourceAssembler() {
    super(TitleController.class, TitleResource.class);
  }
  
  @Override
  public TitleResource toResource(Title entity) {
    TitleResource resource = instantiateResource(entity);
    resource.setEntity(entity);
    return resource;
  }
}
