package me.yukitale.cryptoexchange.panel.worker.repository;

import me.yukitale.cryptoexchange.exchange.model.Coin;
import me.yukitale.cryptoexchange.panel.worker.model.StablePump;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StablePumpRepository extends JpaRepository<StablePump, Long> {

    @CacheEvict(value = "stable_pumps", key = "#result.coin.symbol")
    @Override
    <T extends StablePump> T save(T result);

    @Cacheable(value = "stable_pumps", key = "#coinSymbol")
    Optional<StablePump> findByCoinSymbol(String coinSymbol);

    Optional<StablePump> findById(long id);

    @CacheEvict(value = "stable_pumps", key = "#id")
    @Transactional
    void deleteById(long id);

    @CacheEvict(value = "stable_pumps", key = "#coinSymbol")
    default void deleteByIdAndCoinSymbol(long id, String coinSymbol) {
        deleteById(id);
    }

    @CacheEvict(value = "stable_pumps", allEntries = true)
    @Transactional
    void deleteAllByCoin(Coin coin);
}
