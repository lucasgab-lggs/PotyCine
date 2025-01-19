package br.ufrn.imd.PotyCine.services;

import br.ufrn.imd.PotyCine.domain.Producer;
import br.ufrn.imd.PotyCine.domain.User;
import br.ufrn.imd.PotyCine.dto.ProducerDto;
import br.ufrn.imd.PotyCine.enums.Role;
import br.ufrn.imd.PotyCine.repositories.ProducerRepository;
import br.ufrn.imd.PotyCine.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final UserRepository userRepository;
    private final ProducerRepository producerRepository;

    public ProducerService(UserRepository userRepository, ProducerRepository producerRepository) {
        this.userRepository = userRepository;
        this.producerRepository = producerRepository;
    }

    public Producer createProducer(ProducerDto createProducerDto) {
        User user = userRepository.findById(createProducerDto.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getRole().equals(Role.PRODUCER)) {
            throw new RuntimeException("Apenas usuários com o papel 'PRODUCER' podem ser associados a um produtor.");
        }

        if (producerRepository.existsByUser(user)) {
            throw new RuntimeException("O usuário já está associado a um produtor.");
        }

        Producer producer = new Producer();
        producer.setUser(user);
        producer.setCompanyName(createProducerDto.companyName());
        producer.setPortfolio(createProducerDto.portfolio());

        return producerRepository.save(producer);
    }

    public Producer findProducerById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        try{
            return producerRepository.findByUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Usuário não possui produtor registrado");
        }

    }

    public Producer updateProducer(Long id, ProducerDto producerDto) {
        Producer producer = findProducerById(id);

        if (producerDto.companyName() != null) producer.setCompanyName(producerDto.companyName());
        if (producerDto.portfolio() != null) producer.setPortfolio(producerDto.portfolio());

        return producerRepository.save(producer);
    }
}
