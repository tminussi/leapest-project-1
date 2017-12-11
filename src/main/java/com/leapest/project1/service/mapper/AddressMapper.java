package com.leapest.project1.service.mapper;

import com.leapest.project1.api.dto.AddressDTO;
import com.leapest.project1.api.dto.builder.AddressDTOBuilder;
import com.leapest.project1.dal.entity.Address;
import com.leapest.project1.dal.entity.dv.AddressType;
import org.assertj.core.util.Strings;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {

    public static Address makeAddress(AddressDTO addressDTO) {
        Long id = !Strings.isNullOrEmpty(addressDTO.getId()) ? Long.valueOf(addressDTO.getId()) : null;
        AddressType type = !Strings.isNullOrEmpty(addressDTO.getType()) ? AddressType.valueOf(addressDTO.getType()) : null;

        return new Address(id, type, addressDTO.getFirstName(), addressDTO.getLastName(), addressDTO.getStreet(), addressDTO.getState(), addressDTO.getCountry(), addressDTO.getEmail(), addressDTO.getPhone());
    }


    public static AddressDTO makeAddressDTO(Address address) {
        AddressDTOBuilder builder = AddressDTO.newBuilder()
                .withId(address.getId())
                .withType(address.getType())
                .withFirstName(address.getFirstName())
                .withLastName(address.getLastName())
                .withStreet(address.getStreet())
                .withState(address.getState())
                .withCountry(address.getCountry())
                .withEmail(address.getEmail())
                .withPhone(address.getPhone());

        return builder.createAddressDTO();
    }


    public static List<AddressDTO> makeAddressDTOList(Collection<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::makeAddressDTO)
                .collect(Collectors.toList());
    }
}
