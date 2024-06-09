import CryptoJS from "crypto-js";
import forge from "node-forge";
import {environment} from "../../../environments/environment";

export function generate(body: any) {
    const chipperPayload = encryptAes(JSON.stringify(body));
    const chipperRsa = {iv: chipperPayload.iv, key: chipperPayload.key};
    console.log('body', JSON.stringify(body))
    console.log('chipperPayload', chipperPayload);
    console.log('chipperRsa', chipperRsa);
    const rsaEncrypted = encryptRsa(JSON.stringify(chipperRsa))
    return {
        content: chipperPayload.chipperText.toString(),
        encryptionData: rsaEncrypted
    }
}

export function obtain(content: string, encryptionData: string) {

}

function encryptAes(value: string) {
    const iv = CryptoJS.lib.WordArray.random(8).toString();
    const key = CryptoJS.lib.WordArray.random(8).toString();
    const chipperText = encryptAesJs(
        CryptoJS.enc.Utf8.parse(value),
        CryptoJS.enc.Utf8.parse(key),
        CryptoJS.enc.Utf8.parse(iv));
    return {
        key: key.toString(),
        iv: iv.toString(),
        chipperText: chipperText.toString()
    }

}

function encryptAesJs(value: any, key: any, iv:any) {
    return CryptoJS.AES.encrypt(value, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
}

function encryptRsa(value: string) {
    const publicKey = forge.pki.publicKeyFromPem(environment.encryption.rsaKey);
    return forge.util.encode64(
        publicKey.encrypt(value, 'RSAES-PKCS1-V1_5', {
            md: forge.md.sha256.create(),
        }),
    );
}


