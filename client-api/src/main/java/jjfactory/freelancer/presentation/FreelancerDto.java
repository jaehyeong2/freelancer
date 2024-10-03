package jjfactory.freelancer.presentation;

public class FreelancerDto {
    public record Create(
            String lastName,
            String firstName,
            String phone
    ) {
    }
}
