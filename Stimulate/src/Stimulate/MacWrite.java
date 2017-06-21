/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 *
 * @author user
 */
public class MacWrite {

    private static final short VENDOR_ID = 0x2a19;
    private static final short PRODUCT_ID = 0x801;
    private static final byte INTERFACE = 1;
    private static final byte OUT_ENDPOINT = 0x03;
    private static final int TIMEOUT = 5000;
    private static DeviceHandle handle;
 
    /**
     * Main method.
     * 
     * @param s
     * @param args
     *            Command-line arguments (Ignored)
     * @throws Exception
     *             When something goes wrong.
     */
    public MacWrite()
    {
        
    }
    
    
    public void Write(String s) throws InterruptedException{

        s ="\rgpio writeall 0" + s + "\r";
 
        // Initialize the libusb context
        int result = LibUsb.init(null);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to initialize libusb", result);
        }
        
        // Open test device (Samsung Galaxy Nexus)
        handle = LibUsb.openDeviceWithVidPid(null, VENDOR_ID,
            PRODUCT_ID);
        if (handle == null)
        {
            System.err.println("Test device not found.");
            System.exit(1);
        }
        
 System.out.println(handle + "       "  + s);
        // Claim the ADB interface
        result = LibUsb.claimInterface(handle, INTERFACE);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to claim interface", result);
        }

        // Send message
        String r = "\rgpio iodir 00\r";
        boolean b = false;
        outWright(handle, (byte[]) r.getBytes(StandardCharsets.UTF_8));
        outWright(handle, (byte[]) s.getBytes(StandardCharsets.UTF_8));
        

        // Release the interface
        result = LibUsb.releaseInterface(handle, INTERFACE);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to release interface", result);
        }

        // Close the device
        LibUsb.close(handle);

        // Deinitialize the libusb context
        LibUsb.exit(null);
    }

    private static void outWright(DeviceHandle handle, byte[] data)
    {
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(data.length);
        buffer.put(data);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        int result = LibUsb.bulkTransfer(handle, OUT_ENDPOINT, buffer,
            transferred, TIMEOUT);
        if (result != LibUsb.SUCCESS)
        {
            throw new LibUsbException("Unable to send data", result);
        }
        System.out.println(transferred.get() + " bytes sent to device");
    }
    
}
