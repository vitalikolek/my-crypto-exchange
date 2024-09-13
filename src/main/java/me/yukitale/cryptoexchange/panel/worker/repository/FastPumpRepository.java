package me.yukitale.cryptoexchange.panel.worker.repository;

import me.yukitale.cryptoexchange.exchange.model.Coin;
import me.yukitale.cryptoexchange.panel.worker.model.FastPump;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FastPumpRepository extends JpaRepository<FastPump, Long> {

    @CacheEvict(value = "fast_pumps", key = "#result.coin.symbol")
    <T extends FastPump> T save(T result);

    @Cacheable(value = "fast_pumps", key = "#coinSymbol")
    List<FastPump> findAllByCoinSymbol(String coinSymbol);

    boolean existsByCoinSymbol(String coinSymbol);

    @Transactional
    @CacheEvict(value = "fast_pumps", key = "#coinSymbol")
    void deleteAllByCoinSymbol(String coinSymbol);

    @Transactional
    @CacheEvict(value = "fast_pumps", allEntries = true)
    void deleteAllByCoin(Coin coin);
}
