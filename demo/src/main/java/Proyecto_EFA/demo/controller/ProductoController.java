package Proyecto_EFA.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Producto producto = productoService.getProductoById(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto) {
        Producto createdProducto = productoService.createProducto(producto);
        return ResponseEntity.status(201).body(createdProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto productoDetails) {
        Producto updated = productoService.updateProducto(id, productoDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> partialUpdateProducto(@PathVariable Integer id, @RequestBody Producto productoDetails) {
        Producto updated = productoService.updateProducto(id, productoDetails);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/buscar/marca/{marcaId}")
    public ResponseEntity<List<Producto>> getProductosByMarca(@PathVariable Integer marcaId) {
        List<Producto> productos = productoService.getProductosByMarca(marcaId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Integer categoriaId) {
        List<Producto> productos = productoService.getProductosByCategoria(categoriaId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/color/{colorId}")
    public ResponseEntity<List<Producto>> getProductosByColor(@PathVariable Integer colorId) {
        List<Producto> productos = productoService.getProductosByColor(colorId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/material/{materialId}")
    public ResponseEntity<List<Producto>> getProductosByMaterial(@PathVariable Integer materialId) {
        List<Producto> productos = productoService.getProductosByMaterial(materialId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/talla/{tallaId}")
    public ResponseEntity<List<Producto>> getProductosByTalla(@PathVariable Integer tallaId) {
        List<Producto> productos = productoService.getProductosByTalla(tallaId);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/rango-precio")
    public ResponseEntity<List<Producto>> getProductosByPriceRange(
            @RequestParam Double precioMin,
            @RequestParam Double precioMax) {
        List<Producto> productos = productoService.getProductosByPriceRange(precioMin, precioMax);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Producto>> searchProductosByNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.searchProductosByNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/nombre-contiene")
    public ResponseEntity<List<Producto>> searchProductosByNombreContiene(@RequestParam String nombre) {
        List<Producto> productos = productoService.searchByNombreContaining(nombre);
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/marca/{marcaId}/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getProductosByMarcaAndCategoria(@PathVariable Integer marcaId, @PathVariable Integer categoriaId) {
        List<Producto> productos = productoService.getProductosByMarcaAndCategoria(marcaId, categoriaId);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/top/mas-caros")
    public ResponseEntity<List<Producto>> getTop10MostExpensiveProducts() {
        List<Producto> productos = productoService.getTop10MostExpensiveProducts();
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/top/mas-baratos")
    public ResponseEntity<List<Producto>> getTop10CheapestProducts() {
        List<Producto> productos = productoService.getTop10CheapestProducts();
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/top/mas-caros/{limit}")
    public ResponseEntity<List<Producto>> getTopMostExpensiveProducts(@PathVariable int limit) {
        List<Producto> productos = productoService.getTop10MostExpensiveProducts(); 
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/stock")
    public ResponseEntity<List<Producto>> getProductosConStock() {
        List<Producto> productos = productoService.getProductosConStock();
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/buscar/codigo/{codigo}")
    public ResponseEntity<Producto> getProductoByCodigo(@PathVariable String codigo) {
        return productoService.getProductoByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}