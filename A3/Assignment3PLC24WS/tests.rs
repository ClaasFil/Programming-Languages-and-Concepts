#![allow(warnings)] 



use crate::complex::Complex;
use crate::image::{Pixel, Image};
use crate::client;
use crate::mandelbrot::Mandelbrot;

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn pixels() {
        // test pixels and image
        let p1 = Pixel{r: 255, g: 0, b: 0};
        let p2 = p1;

        // println!("{}", p1); // will work only if copy and display traits are imlpemented

        assert_eq!(p1, p2); // will fail unless traits are set

        let test_pixel_str = format!("{}", p1); // display trait required

        assert_eq!(test_pixel_str, String::from("255 0 0")); // display trait required

        let img1 = Image::new(100, 200);

        let element_0_0 = img1.get(0, 0).unwrap();

        assert_eq!(img1.width, 100);
        assert_eq!(img1.height, 200);
        assert_eq!(element_0_0, &Pixel{r: 0, g: 0, b: 0});
        assert_eq!(img1.get(0,1), Some(&Pixel{r: 0, g: 0, b: 0}));

        let mut img2 = Image::new(200, 100);
        
        *img2.get_mut(0, 0).unwrap() = Pixel{r: 1, g: 0, b: 0};

        let element_0_0_mut = img2.get_mut(0,0).unwrap();
        
        assert_eq!(element_0_0_mut, &Pixel{r: 1, g: 0, b: 0});
        
    }

    #[test]
    fn complex_v1() {
        let c1 = Complex{re: 5.0, im: 3.0};
        let c2 = Complex{re: 2.0, im: 4.0};
        let c_add_1 = c1 + c2;

        if ((c_add_1.re - 7.0)*(c_add_1.re - 7.0)).sqrt() > 0.00001 {
            println!("Error adding two complex numbers.");
            assert!(false);
        } 
    }

    #[test]
    fn complex_v2() {
        let c1 = Complex{re: 5.0, im: 3.0};
        let c2 = Complex{re: 2.0, im: 4.0};
        let c_mul_1 = c1 * c2;

        if ((c_mul_1.re - 2.0)*(c_mul_1.im - 26.0)).sqrt() > 0.00001 {
            println!("Error multiplying two complex numbers.");
            assert!(false);
        } 
    }

    #[test]
    fn complex_v3() {
        let c1 = Complex{re: 5.0, im: 3.0};
        let c_mag = c1.mag();

        if ((c_mag - 34.0)*(c_mag - 34.0)).sqrt() > 0.00001 {
            println!("Error calculating magnitute squared");
            assert!(false);
        } 
    }

    #[test]
    fn check_pixel_v1() {
        let c = Complex{ re: -0.875, im:  0.0 };

        let pixel_is_in_the_set = mandelbrot::check_pixel(c, 1024);
        
        assert_eq!(pixel_is_in_the_set, None);
    }
    
    #[test]
    fn check_pixel_v2() {
        let c = Complex{ re: -0.1, im:  0.9 };

        let pixel_is_in_the_set = mandelbrot::check_pixel(c, 1024);
        
        assert_eq!(pixel_is_in_the_set.is_some(), true);
    }

    #[test]
    fn mandelbrot_generate_and_test_pixel_count_v1() {
        let image = mandelbrot::generate_image(525, 300, 1024);

        let pixels_in_the_set = image.get_mandelbrot_pixels();
        println!("Pixels in the set: {}", pixels_in_the_set);
        
        assert_eq!(pixels_in_the_set, 34062);

        client::save_to_file(&image, "test1.ppm");
    }

    #[test]
    fn mandelbrot_generate_and_test_pixel_count_v2() {
        let image = mandelbrot::generate_image(300, 525, 1024);

        let pixels_in_the_set = image.get_mandelbrot_pixels();
        println!("Pixels in the set: {}", pixels_in_the_set);
        
        assert_eq!(pixels_in_the_set, 33965);

        client::save_to_file(&image, "test2.ppm");
    }
}

